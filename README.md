# kegan

> We see evil cloaking our planet like a black cloud. [...] We see the Jedi
> surrounded by darkness. [...] The darkness comes from within them and then
> spreads to engulf them. Perhaps our destruction will come from an explosive
> device sent to destroy an entire planet without a shiver. [...] We see
> masked soldiers. We do not know who they are, or what they want. Only that
> they are evil. They will bring fear and suffering.
>
> -- O-Vieve and V-Tan, in *Jedi Apprentice: The Fight for Truth*

Monitors a changing structured value and tells you *how* it changes. This
produces an immutable log of events, modeled as fact assertions and
retractions. This lets you tie mutable data to a system that expects immutable
data. It is useful if you want to query both the current state and the
evolution of that state, a la [Datalog][datalog] in [Datomic][datomic] and
[Datascript][datascript]. It supports arbitrarily nested and structured data
automatically by inspecting its structure, with no code to write. For example,
if you want to monitor a JSON REST API (push or pull) and log changes to a log
processor, `kegan` is for you.

[datalog]: https://en.wikipedia.org/wiki/Datalog
[datomic]: http://www.datomic.com/
[datascript]: https://github.com/tonsky/datascript

## License

Copyright © 2015 Rackspace Hosting, Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
